import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  isDarkMode: boolean = false;
  logoLoaded: boolean = true;

  constructor() {
    if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {
      this.isDarkMode = true;
    }
  }

  toggleDarkMode() {
    this.isDarkMode = !this.isDarkMode;
  }

  onSubmit() {
    if (!this.isDarkMode) {
      console.log('Login submitted in light mode');
    } else {
      console.log('Login submitted in dark mode');
    }
  }
}
