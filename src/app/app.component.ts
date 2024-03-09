import { Component, OnInit } from '@angular/core';
import { StylisteService } from './styliste.service';
import { Observable}  from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

public stylist;

constructor(private stylisteService: StylisteService ,private router:Router) {}

  ngOnInit():void {

    this.getStylistes();
  }
  private getStylistes() {
    this.stylisteService.getResource("/api/stylists")
    .subscribe(data=>{ this.stylist= data;
    },err=>{
      console.log(err);
    })

//


  }
}





