import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  constructor(private http:HttpClient){}
  title = 'FitnessApp';
  ngOnInit(){
    this.http.get<any>("http://localhost:8000/api/fitness/all").subscribe(response =>{
      console.log(response);
    })
  }
}
