import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {
  elemento = { id: 1, nombre: 'Pepito', apellidos: 'Grillo', edad: 99 };
  esNuevo = false;

  constructor() { }

  ngOnInit(): void {
  }

  nuevo() {
    this.elemento = { id: null, nombre: null, apellidos: null, edad: null };
    this.esNuevo = true;
  }

  cargar() {
    this.elemento = { id: 2, nombre: 'Carmelo', apellidos: 'Coton del Amor Mundano', edad: 22 };
    this.esNuevo = false;
  }

  enviar() {
    alert(JSON.stringify(this.elemento));
    this.esNuevo = false;
  }
  cancelar() {

  }
}
