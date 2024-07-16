<template>
  <div>
    <h1 class="tituloPag">GALERIA</h1>
    <div class="galeria">
      <div class="cuadroimagen">
        <div v-for="elem in imagenes" :key="elem.ID_Imagen">
          <img :src="'data:image/png;base64,' + elem.imagenes" alt="tattoo" />
        </div>
        <div>
            <img src="../assets/ImagenesTattoos/realista2.jpg" alt="tattoo8" />
        </div>
        <div>
            <img src="../assets/ImagenesTattoos/2.jpg" alt="tattoo4" />
        </div>
        <div>
            <img src="../assets/ImagenesTattoos/realista.jpg" alt="tattoo5" />
        </div>
        <div>
            <img src="../assets/ImagenesTattoos/1.webp" alt="tattoo3" />
        </div>
        <div>
            <img src="../assets/ImagenesTattoos/7.webp" alt="tattoo9" />
        </div>
        <div>
            <img src="../assets/ImagenesTattoos/acuarela2.jpg" alt="tattoo7" />
        </div>
        <div>
            <img src="../assets/ImagenesTattoos/acuarela.jpg" alt="tattoo6" />
        </div>
      </div>
    </div>
  </div>
</template>
  
<script>
export default {
  name: "galeriaVista",
  data() {
    return {
      imagenes: [],
      img: "",
      img2: "",
    };
  },
  methods: {
    recogerImagenes() {
        fetch('http://localhost:8080/tiendaTattoos/imagenes')
                    .then(response => response.json())
                    .then(json => this.imagenes = json);
    },
  },
  created() {
    this.recogerImagenes();
  },
};
</script>
  
<style scoped>
.galeria {
  background-color: rgba(51, 51, 51, 0.8);
}
.cuadroimagen {
  grid-area: seccion;
  height: 100%;
  width: 80%;
  margin: 2% auto;
  padding: 0.7%;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr;
  align-items: stretch;
  grid-gap: 1%;
}

.cuadroimagen > div {
  background-color: azure;
}

.cuadroimagen > div > img {
  height: 100%;
  width: 100%;
}

@media screen and (max-width: 768px) {
  .cuadroimagen {
    grid-template-columns: 1fr 1fr;
    grid-template-rows: 1fr 1fr 1fr 1fr;
    margin: 4% auto;
  }
}

@media screen and (max-width: 480px) {
  .cuadroimagen {
    grid-template-columns: 1fr;
    grid-template-rows: 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr;
    margin: 4% auto;
  }
}

</style>