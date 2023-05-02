

import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FormControl,FormGroup,FormBuilder,Validators } from '@angular/forms';
import { debounceTime, tap, switchMap, finalize, distinctUntilChanged, filter } from 'rxjs/operators';
import {faArrowLeft} from '@fortawesome/free-solid-svg-icons'
import {faClockFour} from '@fortawesome/free-regular-svg-icons'

declare var $: any;  
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  faTrash = faArrowLeft
  faClockFour = faClockFour
  ModalForm = new FormGroup({
    email: new FormControl('', [Validators.required,Validators.email]),
    date: new FormControl('', [Validators.required]),
    hour: new FormControl('', [Validators.required]),
    minute: new FormControl('', [Validators.required])
  }); 
  searchMoviesCtrl = new FormControl();
  cacheData:any[] = [];
  keywordModel: any = ""
  filteredMovies: any;
  submitted = false;
  isLoading = false;
  minLengthTerm = 3;
  emailMessage = ""
  searchKey: any = "";
  distance = 10;
  location = "";
  category = "All";
  latitude = 0;
  longitude = 0;
  imageSrc= "twitter.jpg"
  obj = {'name':"",'email':"", 'date':"","hours":"", "mins":""}
  mapOptions : google.maps.MapOptions={}
  marker = {position:{lat:0,lng:0}}
  hours = ""
  mins = ""
  date = ""
  email = ""
  cancelBtn = false;
  arr=[{email:"",date:"",hours:"",mins:"",name:""}]
  ls : any;
  title = 'businessProject';
  myDate = Date.now();
  @ViewChild('inputField')
  loc!: ElementRef;
  @ViewChild('modal')
  modal!: ElementRef;
  autodetect = false;
  disableLoc = false;
  constructor(private http: HttpClient) { 

}
  keyword = ""
  businessInfo = [{ name: "", image_url: "", distance: 0, rating: "", id: "" }]
  cardInfo = { name: "", url: "", price: "", photos: [], hours: [{ is_open_now: false }], categories: [{ title: "" }], phone: "", location: { address1: "", address2: "", address3: "", city: "", zip_code: "" },coordinates:{latitude:0,longitude:0} }
  reviews = [{ rating: 0, text: "", user: { name: "" }, time_created: "" }]
  cardHidden = true;
  categories = ""
  storeStatus = ""
  time = ["10", "11", "12", "13", "14", "15", "16", "17"]
  minutes = ["00", "15", "30", "45"]
  searchBaby() {
    if(this.autodetect){
      const params = new HttpParams().append('keyword', this.searchKey,).append('latitude', this.latitude).append('longitude', this.longitude).append('category', this.category).append('distance', this.distance* 1609.344).append('ad', true);
      this.http.get<any>('https://reesespeanutbutter123.wn.r.appspot.com/getData',{params}).subscribe(data => {
        console.log(data)
        this.businessInfo = data
      })
    }else{
    const params = new HttpParams().append('keyword', this.searchKey,).append('location', this.location).append('category', this.category).append('distance', this.distance* 1609.344).append('ad', false);
    this.http.get<any>('https://reesespeanutbutter123.wn.r.appspot.com/getData',{params}).subscribe(data => {
      console.log(data)
      this.businessInfo = data
    })
    this.cardHidden = true;
  }
}

  milesConversion(val: number) {
    return Math.round(((val / 1609.344) * 100) / 100)
  }
  hideCard(){
    this.cardHidden = true;
  }
  clear(){
    this.searchKey=""
    this.distance=10
    this.category="All"
    this.location=""
    this.businessInfo= []
    this.cardHidden = true
    this.filteredMovies = []
    this.autodetect = false;
  }

  showCardData(obj: { id: string | number | boolean; }) {
    this.email="";
    this.date="";
    this.hours="";
    this.mins=""
    const params = new HttpParams().append('id', obj.id);
    this.http.get<any>('https://reesespeanutbutter123.wn.r.appspot.com/cardDetails', { params }).subscribe(data => {
      this.cardHidden = false
      this.cardInfo = data.cardInfo
      this.reviews = data.reviews.reviews
      for(let i=0;i<this.reviews.length;i++){
        this.reviews[i].time_created = this.reviews[i].time_created.replace(/\s.*/,"")
      }
      this.ls = localStorage.getItem('data')?localStorage.getItem('data'):'';
      if(this.ls !=""){
        this.arr = JSON.parse(this.ls)
        this.cancelBtn = false;
        let i= 0;  
        for( i=1;i<this.arr.length;i++){
          if(this.arr[i]["name"] == this.cardInfo.name){
            this.cancelBtn = true;
          }
        }
    }
      this.mapOptions = {
        center: { lat: this.cardInfo.coordinates.latitude, lng: this.cardInfo.coordinates.longitude },
        zoom: 14
      };
      this.marker = {
        position: { lat: this.cardInfo.coordinates.latitude, lng: this.cardInfo.coordinates.longitude},
     }
      this.categories = "";
      for (let i = 0; i < this.cardInfo.categories.length; i++) {
        if (i == this.cardInfo.categories.length - 1) {
          this.categories += this.cardInfo.categories[i].title
        } else {
          this.categories += this.cardInfo.categories[i].title + " | "
        }
      }
      this.storeStatus = this.cardInfo.hours[0].is_open_now ? "Open Now" : "Closed";
    })
  }
  autoDetectLoc(){
    this.autodetect = !this.autodetect;
    if(this.autodetect){
      this.location = ""
    }
    const params = new HttpParams().append('keyword', this.searchKey,).append('location', this.location).append('category', this.category).append('distance', this.distance* 1609.344);
      this.http.get<any>("https://ipinfo.io/json?token=a16b27871e6170").subscribe(data => {
        console.log(data)
        this.latitude = parseFloat(data.loc.split(',')[0])
        this.longitude = parseFloat(data.loc.split(',')[1])
      })
  }
  onSelected() {
    console.log(this.searchKey);
    this.loc.nativeElement.value = this.searchKey
  }

  displayWith(value: any) {
    return value?.Title;
  }

  ngOnInit() {
    this.searchMoviesCtrl.valueChanges
      .pipe(
        filter(res => {
          return res !== null && res.length >= this.minLengthTerm
        }),
        distinctUntilChanged(),
        debounceTime(2000),
        tap(() => {
          this.filteredMovies = [];
          this.isLoading = true;
        }),
        switchMap(value => this.http.get('https://reesespeanutbutter123.wn.r.appspot.com/keywordSuggest', { params: { "key": value } })
          .pipe(
            finalize(() => {
              this.isLoading = false
            }),
          )
        )
      )
      .subscribe((data: any) => {
        if (data.categories.length == 0) {
          this.filteredMovies = [];
        } else {
          for (let i = 0; i < data.categories.length; i++) {
            console.log(data.categories[i].title)
            this.filteredMovies.push(data.categories[i].title)
          }
          for (let i = 0; i < data.terms.length; i++)
            this.filteredMovies.push(data.terms[i].text)
        }
      });
  }

  reserve(name:any) {
    this.submitted = true;
    if(!this.ModalForm.get("email")?.hasError("required") && !this.ModalForm.get("email")?.hasError("email") && !this.ModalForm.get("date")?.hasError("required") && !this.ModalForm.get("hour")?.hasError("required") && !this.ModalForm.get("minute")?.hasError("required")){
      this.obj = {'name':name,'email': this.email, 'date':this.date, 'hours':this.hours, 'mins': this.mins}
      let x= JSON.parse(localStorage.getItem('data') || '{}')
      if(x!.length>0){
        for(let i=0;i<x!.length;i++){
          this.cacheData.push(x![i])
        }
      }
      this.cacheData.push(this.obj)
      console.log(this.cacheData)
      localStorage.setItem('data',JSON.stringify(this.cacheData))
      $(document).ready(function(){    
        $("#closeBtn").click(); 
    });
    window.alert("Reservation Created!")
    this.cancelBtn = true;
    } 
  }
  cancelReservation(name:string){
    this.ls = localStorage.getItem('data')?localStorage.getItem('data'):'';
    this.arr = JSON.parse(this.ls)
      window.alert("Reservation Cancelled!")
      console.log(this.arr)
      let i;
      for(i=0;i<this.arr.length;i++){
        if(this.arr[i].name == name){
          break;
        }
      }
      this.cancelBtn= false;
      this.arr.splice(i,1)
      localStorage.setItem('data', JSON.stringify(this.arr))
    }
}
