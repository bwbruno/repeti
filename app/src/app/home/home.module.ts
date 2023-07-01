import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { LoginComponent } from './login/login.component';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { CadastroComponent } from './cadastro/cadastro.component';
import { QuestaoComponent } from './questao/questao.component';
import { MatRadioModule } from '@angular/material/radio';
import { EstudosComponent } from './estudos/estudos.component';
import {DialogModule} from '@angular/cdk/dialog';


@NgModule({
  declarations: [HomeComponent, LoginComponent, CadastroComponent, QuestaoComponent, EstudosComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    FormsModule,
    MatCardModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MatRadioModule,
    ReactiveFormsModule,
    DialogModule
  ],
  exports: [HomeComponent],
})
export class HomeModule {}
