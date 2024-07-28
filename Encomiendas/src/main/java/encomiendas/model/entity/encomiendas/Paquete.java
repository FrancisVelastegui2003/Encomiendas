package encomiendas.model.entity.encomiendas;

import lombok.*;

/**
 * Clase que representa un paquete
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Paquete {

   private Integer idPaquete;
   private Double peso;
   private Double volumen;
   private Boolean isFragil;
   private String descripcion;
   private Integer idEncomienda;

   public Double calcularPrecio(){
       double precio = 0;
       if(peso < 5){
           precio = 10;
       }else if(peso < 10){
           precio = 20;
       }else if(peso < 20){
           precio = 30;
       }else{
           precio = 40;
       }
       if(volumen > 0.5){
           precio += 10;
       }
       if(isFragil){
           precio += 20;
       }
       return precio;
   }

}
