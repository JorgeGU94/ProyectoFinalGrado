<template>
  <div id="contenedorTatuadores">
    <h1 class="tituloPag">Tatuadores</h1>
    <div id="tarjetasTatuadores">
      <div v-for="(item, index) in listaTatuadores" :key="index" class="tarjeta">
        <img :src="item.imagen" alt="">
        <div class="contenidoTarjeta">
          <h2>{{ item.nombre }} {{ item.apellidos }}</h2>
          <p>{{ item.descripcion }}</p>
          <button @click="mostrarcita(item.nombre)">Formulario Cita</button>
        </div>
      </div>
    </div>
    <div :hidden="booleana" ref="formulario">
          <PedirCita :nombreTatuadores="nombreTatuadores"></PedirCita>
    </div>
  </div>
  
</template>
  
<script>

import PedirCita from '@/components/PedirCita.vue';

    export default {
      props: {},
      components:{
        PedirCita
      },
      data() {
        return { 
          listaTatuadores: [],
          imagenes: ["https://picsum.photos/200/200", "https://picsum.photos/200/200/", "https://picsum.photos/200/200//"],
          booleana: true,
          nombreTatuadores: ""
        }
      },
      methods: {
        listarTatuadores() {
          fetch('http://localhost:8080/tiendaTattoos/tatuadores')
            .then(response => response.json())
            .then(json => {
              this.listaTatuadores = json;
              this.listaTatuadores.forEach((element, index) => {
                element.imagen = this.imagenes[index % this.imagenes.length];
              });
            });
        },
        mostrarcita(nombre){
          if (!this.booleana) {
            this.booleana = true;
          }else {
            this.booleana = false;
          }
          this.nombreTatuadores = nombre;
          this.$nextTick(() => {
            this.$refs.formulario.scrollIntoView({ behavior: 'smooth' });
          });
        }
      },
      created() {
       this.listarTatuadores(); 
      }
    };
</script>
  
<style>
#contenedorTatuadores{
  display: flex;
  flex-direction: column;
}

.tituloPag{
  margin: 4% auto 2%;
  padding: 1%;
  width: 20%;
  text-align: center;
  color: white;
  background-color: rgba(51, 51, 51, 0.8);
  border-radius: 18px;
}

#tarjetasTatuadores{
  display: flex;
  flex-direction: column;
  font-size: large;
  background-color: rgba(51, 51, 51, 0.8);
}

.tarjeta{
  width: 70%;
  background-color: rgba(248, 212, 197, 0.7);
  margin: auto;
  padding: 2%;
  display: flex;
  align-items: center;
}

.tarjeta img{
  border-radius: 50%;
  margin: 3%;
}

.tarjeta p {
  text-align: justify;
  margin-right: 5%;
}

.contenidoTarjeta button{
  margin: 1%;
  border: 0;
  color: white;
  background-color: rgba(51, 51, 51, 0.8);
  padding: 1% 2%;
  transition: 1s;
}

.contenidoTarjeta button:hover{
  cursor: pointer;
  transition: 1s;
  background-color: #fd5437;
}

@media screen and (max-width: 768px) {
 .tarjeta{
  flex-direction: column;
 }

 .contenidoTarjeta h2{ 
  text-align: center;
  width: 100%;
 }

 .contenidoTarjeta button{
  width: 100%;
  padding: 2%;
}
 .tituloPag{
  width: 40%;
 }
}

@media screen and (max-width: 480px) {
  .tituloPag{
    width: 80%;
  }

  .contenidoTarjeta p{ 
    text-align: center;
  }

}

</style>