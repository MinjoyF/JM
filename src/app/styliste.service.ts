import { Injectable } from '@angular/core';
import { HttpClient } from'@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StylisteService {
  public host:String ="http://localhost:8086"

  constructor(private http:HttpClient) { }

  public getResource (url){
    return this.http.get(this.host +url);
  }
  
}
