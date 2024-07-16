<template>
  <div class="pagina">
    <div id="menu">
      <nav>
        <div class="objMenu" @click="cambiarVistaLogeado(0)">
          <router-link to="/">Home</router-link> 
        </div>
        <div class="objMenu" @click="cambiarVistaLogeado(0)">
          <router-link to="/galeria">Galeria</router-link> 
        </div>
        <div class="objMenu" @click="cambiarVistaLogeado(0)"> 
          <router-link to="/tatuadores">Tatuadores</router-link> 
        </div>
        <div class="objMenu" @click="cambiarVistaLogeado(0)">
          <router-link to="/nosotros">Sobre Nosotros</router-link> 
        </div>
        <div class="objMenu nolinea" @click="cambiarVistaLogeado(1)">
          <a>{{texto}}</a>
        </div>
      </nav> 
    </div>
    <div id="vistas">
      <router-view v-if="mostrar" @logout="cerrarSesion"/>
      <login v-if="mostrarlog" @usuarioLogeado="cambiarLogin" ></login>
    </div>
    <div id="footer">
      <div class="copy">
        ©2023-2024 Drawtatoo - Javier - Jorge | Drawtatoo es una empresa de tatuajes | All rights reserved - Todos los derechos reservados
      </div>
      <div class="redes">   
          <a href="#"><img src="./assets/icon-facebook.png" alt="face"></a>
          <a href="#"><img src="./assets/icon-instagram.png" alt="insta"></a>
          <a href="#"><img src="./assets/icon-twitter.png" alt="twt"></a>
      </div>
    </div>
  </div>
</template>

<script>
import login from "./components/LoginComponent.vue";


export default {
  components: {
    login
  },
  data() {
    return {
      login: false,
      mostrar: true,
      mostrarlog: false,
      texto: "Login",
      email: ""
    };
  },
  methods: {
    cerrarSesion(texto) {
      console.log(texto);
      this.login = false;
      this.mostrar = true;
      this.mostrarlog = false;
      this.texto = "Login";
      this.email = "";
      localStorage.clear();
      this.$router.push('/login');
    },
    cambiarLogin(email) {
      this.email = email;
      this.login = true;
      this.texto = "User";
      this.mostrar = true;
      this.mostrarlog = false;
      this.$router.push({ name: "User", params: { email: this.email } });
    },
    async cambiarVistaLogeado(boolean) {
      if (boolean === 1) {
        if (!this.login) {
          this.mostrar = false;
          this.mostrarlog = true;
        } else {
          this.$router.push({ name: "User", params: { email: this.email } });
        }
      } else {
        this.mostrar = true;
        this.mostrarlog = false;
      }
    }
  },
  created() {
    this.email = "";
    localStorage.clear();
  }
};
</script>

<style>

html, body {
      overflow: auto;
      scrollbar-width: none;
      -ms-overflow-style: none;  
      font-family: 'Times New Roman', Times, serif;
}

html::-webkit-scrollbar, body::-webkit-scrollbar {
  display: none; 
}

body {
  margin: 0;
  background-image: url("assets/fondo.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center center;
}

a{
  text-decoration: none;
}

.pagina {
  height: 100vh;
}

#menu{
  display: flex;
  flex-direction: row;
  background-color: rgba(51, 51, 51, 0.8);
  height: 8%;
}

nav{
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
}

.objMenu{
  width: 20%;
  margin: 0;
  padding: 1% 0;
  text-align: center;
  border-right: 2px solid black;
}

.nolinea{
  border: 0;
}

.objMenu a{
  padding: 2% 15%;
  color: white;
  font-size: 20px; 
  transition: 1s;
  cursor: pointer;
}

.objMenu a:hover {
  transition: 1s;
  color: #fd5437;
  font-size: 21px; 
}

#footer{
  display: flex;
  flex-direction: row;
  background-color: rgba(51, 51, 51, 0.8);
  align-items: center;
  height: 7%;
}

.copy{
  width: 80%;
  color: white;
  font-size: smaller;
  text-align: center;
}

.redes{
  width: 20%;
  display: flex;
  flex-direction: row;
  
}

.redes a{
  width: 10%;
  margin: 0 2%;
  align-items: center;
}

.redes a img{
  width: 100%;
}

@media screen and (max-width: 768px) {

  
  #menu{
    flex-direction: column;
    height: auto;
  }

  nav{
    flex-direction: column;
  }

  .objMenu{
    width: 70%;
    border-right: 0px;
    border-bottom: 2px solid black;
  }
  .nolinea{
    border: 0;
  }

  #footer{
    flex-direction: column;
    height: auto;
  }

  .copy{
    width: 90%;
    margin-top: 1%;
    font-size: small;
  }
  
  .redes{
    width: 50%;
    align-self: center;
    justify-content: space-between;
    margin: 1% auto;
  }
}
@media screen and (max-width: 480px) {
  
  .objMenu{
    padding: 2%;
  }

  .objMenu{
    width: 100%;
  }
  
  .redes{
    width: 80%;
    align-self: center;
    justify-content: space-between;
    margin: 5% auto;
  }
}
</style>
