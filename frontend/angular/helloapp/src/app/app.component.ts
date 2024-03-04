import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";

@Component({
    selector: "my-app",
    standalone: true,
    imports: [FormsModule],
    templateUrl: './app.component.html',
    styleUrls: ['../style.css', './app.component.css']
})
export class AppComponent {
    name = "";
    age = 12;
    isRed = true;
}