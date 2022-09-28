import { Component, OnInit } from '@angular/core';
import { StylisteService } from '../styliste.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-model',
  templateUrl: './model.component.html',
  styleUrls: ['./model.component.css']
})
export class ModelComponent implements OnInit {

  public models;

  constructor(private stylisteService: StylisteService,
    private route:ActivatedRoute) { }

  ngOnInit(): void {
    let p1 = this.route.snapshot.params.p1;
    let p2 = this.route.snapshot.params.p2;

    if(p1 == 1){
      this.getModels('/api/models/search/selectedModels');
    }else if(p2 == 2){
      let idStyl = this.route.snapshot.params.p2;
      this.getModels('/stylistes/'+ idStyl+'/models');
    }
  }

  private getModels(url){
    this.stylisteService.getResource(url)
    .subscribe(data => {
      this.models= data;
    },err => {
      console.log(err);
    })
  }

}
