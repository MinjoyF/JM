import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ModelComponent } from './model/model.component';

const routes: Routes = [
  { path:'models/:p1' ,component:ModelComponent},
  { path:'models/:p1/:p2' ,component:ModelComponent},
 { path:'' ,redirectTo:'models/1/0', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
