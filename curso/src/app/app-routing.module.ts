import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalculadoraComponent } from './calculadora/calculadora.component';
import { ContactosAddComponent, ContactosEditComponent, ContactosListComponent, ContactosViewComponent } from './contactos';
import { DemosComponent } from './demos/demos.component';
import { HomeComponent, PageNotFoundComponent } from './main';
import { AuthGuard } from './security';

const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent},
  { path: 'inicio', component: HomeComponent},
  { path: 'demos', component: DemosComponent, data: { pageTitle: 'demos'}},
  { path: 'chisme/de/hacer/numeros', component: CalculadoraComponent, data: { pageTitle: 'calculadora'}},
  { path: 'contactos', component: ContactosListComponent, canActivate: [ AuthGuard ]},
  { path: 'contactos/add', component: ContactosAddComponent},
  { path: 'contactos/:id/edit', component: ContactosEditComponent},
  { path: 'contactos/:id', component: ContactosViewComponent},
  { path: 'contactos/:id/:kk', component: ContactosViewComponent},
  { path: 'personas', children: [
    { path: '', component: ContactosListComponent},
    { path: 'add', component: ContactosAddComponent},
    { path: ':id/edit', component: ContactosEditComponent},
    { path: ':id', component: ContactosViewComponent},
    { path: ':id/:kk', component: ContactosViewComponent},
  ]},
  { path: 'marline/lockton', redirectTo: '/contactos/1'},
  { path: 'config', loadChildren: () => import('./config/config.module').then(mod => mod.ConfigModule)},
  { path: '404.html', component: PageNotFoundComponent},
  { path: '**', component: PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
