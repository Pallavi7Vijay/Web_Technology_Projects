

import { Component, ElementRef, ViewChild} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  selectBtn =window.location.pathname=='/search'?'s': window.location.pathname=='/booking'? 'b' : 's'

  addStyle(g:string){
    if(g=='s'){
      this.selectBtn ="s"
    }else{
      this.selectBtn ="b"
    }
  }
}
