import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

import { Injectable } from '@angular/core';
import { RESTDAOService } from '../base-code/RESTDAOService';
import { NotificationService } from '../common-services';

@Injectable({ providedIn: 'root' })
export class ActoresDaoService extends RESTDAOService<any, number> {
  constructor(http: HttpClient) {
    super(http, 'actores');
  }
}
@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {
  elemento = { id: 1, nombre: 'Pepito', apellidos: 'Grillo', edad: 99 };
  listado = [];
  esNuevo = false;

  constructor(private dao: ActoresDaoService, private notify: NotificationService) { }

  ngOnInit(): void {
  }

  nuevo() {
    this.elemento = { id: null, nombre: null, apellidos: null, edad: null };
    this.esNuevo = true;
  }

  cargar() {
    this.dao.get(this.elemento.id)
      .subscribe(
        datos => {
          this.elemento = datos;
          this.esNuevo = false;
        },
        err => this.notify.add(err.message)
      );
    this.dao.query()
      .subscribe(
        datos => {
          this.listado = datos.content;
        },
        err => this.notify.add(err.message)
      );

    // this.elemento = { id: 2, nombre: 'Carmelo', apellidos: 'Coton del Amor Mundano', edad: 22 };
    // this.esNuevo = false;
  }

  enviar() {
    alert(JSON.stringify(this.elemento));
    this.esNuevo = false;
  }
  cancelar() {

  }
}
