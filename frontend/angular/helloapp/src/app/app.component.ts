import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";

@Component({
    selector: "my-app",
    standalone: true,
    imports: [FormsModule],
    template: `<div class="container">
        <label>Введите имя:</label>
        <input [(ngModel)]="name" placeholder="name">
        <h1>Добро пожаловать {{name}}!</h1>
        <div [class.redbox]="isRed">
            Добро пожаловать {{name}} твой возраст {{age}}!
        </div>
        <input [(ngModel)]="isRed">
    </div>`,
    styles: `.redbox{color: red;}`
})
export class AppComponent {
    name = "";
    age = 12;
    isRed = true;
}