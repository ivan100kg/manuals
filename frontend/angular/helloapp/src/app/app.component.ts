import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { ChildComponent } from "./child.component";

@Component({
    selector: "my-app",
    standalone: true,
    imports: [FormsModule, ChildComponent],
    templateUrl: './app.component.html',
    styleUrls: ['../style.css', './app.component.css']
})
export class AppComponent {
    name = "";
    age = 12;
    isRed = true;
}