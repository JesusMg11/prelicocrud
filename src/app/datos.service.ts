import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

const URL:string = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class DatosService {

  constructor(private http: HttpClient) { }

  obtenerCuentaId(id){
    if(id != ""){
      return this.http.get(URL + "cuentas/" + id);
    }else{
      this.obtenerCuentas();
    }
  }

  obtenerCuentas(){
    return this.http.get(URL + "cuentas");
  }

  crearCuenta(cuenta){
    let formData = new FormData();
    formData.append('id', cuenta.id);
    formData.append('cuenta', cuenta.cuenta);
    return this.http.post(URL + "cuentas", formData);
  }

  putCuenta(cuenta){

    let formData = new FormData();
    formData.append('id', cuenta.id);
    formData.append('cuenta', cuenta.cuenta);

    return this.http.put(URL + "cuentas/" + cuenta.id, formData);
  }

  patchCuenta(cuenta){

    let formData = new FormData();
    formData.append('id', cuenta.id);
    formData.append('cuenta', cuenta.cuenta);
    
    return this.http.patch(URL + "cuentas/" + cuenta.id, formData);
  }

  deleteCuenta(cuenta){
    return this.http.delete(URL + "cuentas/" + cuenta.id);
  }

}
