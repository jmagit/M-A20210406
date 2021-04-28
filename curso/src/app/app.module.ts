import { LOCALE_ID, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { environment } from 'src/environments/environment';
import { ERROR_LEVEL, LoggerService, MyCoreModule } from 'src/my-core';
import { MainModule } from './main';
import { CommonServicesModule } from './common-services';

// Cargar idioma --------------------------------------------------------
import { registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';
import localeEsExtra from '@angular/common/locales/extra/es';

registerLocaleData(localeEs, 'es', localeEsExtra);
// ---------------------------------------------------------------------

import { AppComponent } from './app.component';
import { DemosComponent } from './demos/demos.component';
import { CalculadoraComponent } from './calculadora/calculadora.component';
import { DinamicoComponent } from './dinamico/dinamico.component';
import { FormularioComponent } from './formulario/formulario.component';

@NgModule({
  declarations: [
    AppComponent,
    DemosComponent,
    CalculadoraComponent,
    DinamicoComponent,
    FormularioComponent,
  ],
  imports: [
    BrowserModule, FormsModule, HttpClientModule,
    MainModule, CommonServicesModule, MyCoreModule,
    AppRoutingModule
  ],
  providers: [
    LoggerService,
    { provide: ERROR_LEVEL, useValue: environment.ERROR_LEVEL },
    { provide: LOCALE_ID, useValue: 'es-ES' },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
