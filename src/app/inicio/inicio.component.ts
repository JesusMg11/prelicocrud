import { Component, OnInit } from '@angular/core';
import { DatosService } from '../datos.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  constructor(private datos: DatosService) { }

  cuentas:any;
  cuentaCrear:any = {id:'0', cuenta: '0'};
  cuentaAuxiliar = {id:'0', cuenta: '0'};
  buscador = "";

  obtenerCuentas(){
    this.datos.obtenerCuentas().subscribe(respuesta=>{
      this.cuentas = respuesta;
      this.buscador = "";
    },error=>{
      console.log(error);
    });
  }

  obtenerCuentaId(){
    if(this.buscador != ""){
      this.datos.obtenerCuentaId(this.buscador).subscribe(respuesta=>{
        if(respuesta != null){
          this.cuentas = respuesta;
          this.cuentas = [this.cuentas];
        }else{
          this.cuentas = respuesta;
        }
      },error=>{
        console.log(error);
      });
    }else{
      this.obtenerCuentas();
    }
  }

  agregarCuenta(){
    this.datos.crearCuenta(this.cuentaCrear).subscribe(respuesta=>{
      if(respuesta["respuesta"] == "si"){
        this.obtenerCuentas();
        this.cuentaCrear.cuenta = "";
      }else{
        console.log("error");
      }
    },error=>{
      console.log(error);
    })
  }

  llenarAuxiliar(item){
    this.cuentaAuxiliar.cuenta = item.cuenta;
    this.cuentaAuxiliar.id = item.id;
  }

  putCuenta(){
    this.datos.putCuenta(this.cuentaAuxiliar).subscribe(respuesta=>{
      if(respuesta["respuesta"] == "si"){
        this.obtenerCuentas();
        this.cuentaAuxiliar.cuenta = "0";
        this.cuentaAuxiliar.id = "0";
      }else{
        console.log("error")
      }
    },error=>{
      console.log(error);
    });
  }

  patchCuenta(){
    this.datos.patchCuenta(this.cuentaAuxiliar).subscribe(respuesta=>{
      if(respuesta["respuesta"] == "si"){
        this.obtenerCuentas();
        this.cuentaAuxiliar.cuenta = "0";
        this.cuentaAuxiliar.id = "0";
      }else{
        console.log("error")
      }
    },error=>{
      console.log(error);
    });
  }

  deleteCuenta(){
    this.datos.deleteCuenta(this.cuentaAuxiliar).subscribe(respuesta=>{
      if(respuesta["respuesta"] == "si"){
        this.obtenerCuentas();
        this.cuentaAuxiliar.cuenta = "0";
        this.cuentaAuxiliar.id = "0";
      }
      else{
        console.log("error")
      }
    },error=>{
      console.log(error)
    });
  }

  ngOnInit(): void {
    this.obtenerCuentas();
  }

}
