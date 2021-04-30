import { Component, OnInit } from '@angular/core';
import { ActoresComponent } from '../actores';
import { CalculadoraComponent } from '../calculadora/calculadora.component';
import { ContactosComponent } from '../contactos';
import { DemosComponent } from '../demos/demos.component';
import { FormularioComponent } from '../formulario/formulario.component';
import { HomeComponent } from '../main';

@Component({
  selector: 'app-dinamico',
  templateUrl: './dinamico.component.html',
  styleUrls: ['./dinamico.component.css']
})
export class DinamicoComponent implements OnInit {
  menu = [
    {texto: 'actores', componente: ActoresComponent },
    {texto: 'inicio', componente: HomeComponent },
    {texto: 'demos', componente: DemosComponent },
    {texto: 'calculadora', componente: CalculadoraComponent },
    {texto: 'formulario', componente: FormularioComponent },
    {texto: 'contactos', componente: ContactosComponent },
  ];
  actual = this.menu[0].componente;

  constructor() { }

  selecciona(indice: number) {
    this.actual = this.menu[indice].componente;
  }

  ngOnInit(): void {
  }

}
