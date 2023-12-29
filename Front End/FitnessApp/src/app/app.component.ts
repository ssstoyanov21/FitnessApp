import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { FitnessService } from './services/fitness.service';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'FitnessApp';
  constructor(private fitnessService:FitnessService){}

  ngOnInit() {
    this.fitnessService.getAllFitnesses().subscribe(response => console.log(response));
  }
}
