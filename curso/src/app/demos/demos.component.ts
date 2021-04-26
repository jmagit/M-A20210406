import { Component, OnDestroy, OnInit } from '@angular/core';
import { Unsubscribable } from 'rxjs';
import { LoggerService } from 'src/my-core';
import { NotificationService, NotificationType } from '../common-services';

@Component({
  selector: 'app-demos',
  templateUrl: './demos.component.html',
  styleUrls: ['./demos.component.css']
})
export class DemosComponent implements OnInit, OnDestroy {
  private suscriptor: Unsubscribable;

  private nombre: string = 'mundo';
  listado = [
    { id: 1, nombre: 'Toledo'},
    { id: 2, nombre: 'CUENCA'},
    { id: 3, nombre: 'albacete'},
    { id: 4, nombre: 'ciudad Real'},
  ];
  idProvicia = 2;

  visible = true;
  estectica = { importante: true, error: false, urgente: true, };
  fontSize = 24;

  resultado: string = null;

  constructor(public vm: NotificationService, private out: LoggerService) { }

  get Nombre(): string { return this.nombre; }
  set Nombre(valor: string) {
    if(this.nombre === valor) return;
    // validacion
    // notificar antes de cambiar
    this.nombre = valor;
    // notificar despues de cambiar
  }

  saluda() {
    this.resultado = `Hola ${this.Nombre}`;
  }
  despide() {
    this.resultado = `Adios ${this.Nombre}`;
  }
  di(algo: string) {
    this.resultado = `Dice ${algo}`;
  }

  calcula(a: number, b: number): number { return a + b; }

  cambia() {
    this.visible = !this.visible;
    this.estectica.importante = !this.estectica.importante;
    this.estectica.error = !this.estectica.error;
  }

  add(provincia: string) {
    const id = this.listado.length === 0 ? 1 : (this.listado[this.listado.length - 1].id + 1);
    this.listado.push({ id,  nombre: provincia});
    this.idProvicia = id;
  }

  ngOnInit(): void {
    // this.suscriptor = this.vm.Notificacion.subscribe(n => {
    //   if (n.Type !== NotificationType.error) { return; }
    //   window.alert(`Suscripcion: ${n.Message}`);
    //   this.vm.remove(this.vm.Listado.length - 1);
    // });
  }
  ngOnDestroy(): void {
    // if (this.suscriptor) {
    //   this.suscriptor.unsubscribe();
    // }
  }
}
