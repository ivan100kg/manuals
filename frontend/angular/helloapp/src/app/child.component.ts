import { Component } from "@angular/core";

@Component({
    selector: 'child-comp',
    standalone: true,
    template: `<ng-content></ng-content>
               <h2>Еще раз велком наш друг! {{name}}</h2>`,
    styles: [`h2{color:navy;}`]
})
export class ChildComponent {
    name = 'Кузя';
}