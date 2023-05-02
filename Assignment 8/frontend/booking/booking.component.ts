import { Component, OnInit } from '@angular/core';
import {faTrashCan} from '@fortawesome/free-regular-svg-icons'

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  faTrashsh=faTrashCan
  constructor() { }
  tableData=[]
  arr=[{email:"",date:"",hours:"",mins:"",name:""}]
  ls : any;
  ngOnInit(): void {
    this.ls = localStorage.getItem('data')?localStorage.getItem('data'):'';
    this.arr = JSON.parse(this.ls)
  }
  deleteReservation(name : string){
    window.alert("Reservation Cancelled!")
    console.log(this.arr)
    let i;
    for(i=0;i<this.arr.length;i++){
      if(this.arr[i].name == name){
        break;
      }
    }
    this.arr.splice(i,1)
    localStorage.setItem('data', JSON.stringify(this.arr))
  }
}
