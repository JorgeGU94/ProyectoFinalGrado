<template>
  <div class="formulario">
    <h1>{{titulo}}</h1>
    <div class="contenido">
      <div class="form-group" v-if="registro">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" v-model="nombre" class="form-control" required />
      </div>
      <div class="form-group" v-if="registro">
        <label for="apellidos">Apellidos:</label>
        <input type="text" id="apellidos" v-model="apellidos" class="form-control" required />
      </div>
      <div class="form-group" v-if="registro">
        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" v-model="telefono" class="form-control" required />
      </div>
      <div class="form-group">
        <label for="username">Correo electrónico:</label>
        <input type="email" id="email" v-model="email" class="form-control" required />
      </div>
      <div class="form-group">
        <label for="password">Contraseña:</label>
        <input type="password" id="password" v-model="password" class="form-control" required />
      </div>
      <div class="form-group" v-if="registro">
        <label for="password">Confirma la contraseña:</label>
        <input type="password" id="password" v-model="password2" class="form-control" required />
      </div>
      <div>
        <button type="button" class="btn btn-primary" @click="confirmarInicioSesion()" v-if="!registro">Iniciar sesión</button>
        <button type="button" class="btn btn-primary" @click="cambiarInici()">Registrarse</button>
      </div>
    </div>
    <p v-if="registro" @click="cambiarInici()" class="sicuenta">Tengo una cuenta</p>
    <div class="error-message" v-if="errorMessage">{{ errorMessage }}</div>
    
  </div>
</template>
  
<script>
export default {
  data() {
    return {
      listaClientes: [],
      errorMessage: null,
      registro: false,
      titulo: "Iniciar sesión",
      nombre:"",
      apellidos:"",
      telefono:"",
      email: "",
      password: "",
      password2: ""
    };
  },
  methods: {
    cambiarInici(){
      if (this.registro) {
        this.confirmarResgistroSesion();
      }else {
        this.registro=true;
        this.errorMessage = null;
        this.titulo = "Resgistrarse";
        this.nombre = "";
        this.apellidos = "";
        this.telefono ="";
        this.email = ""
        this.password = "";
        this.password2 = "";
      }
    },
    confirmarInicioSesion(){
      this.errorMessage = "";
      let log = false;
      for (let i = 0; i < this.listaClientes.length; i++) {
        if (this.listaClientes[i].email === this.email && this.listaClientes[i].password === this.password) {
          log = true;
        }
      }
      if (log) {
        this.emitirLogin();
      }else {
        this.errorMessage = "Error al iniciar sesion";
        this.password = "";
      }
    },
    emitirLogin(){
      this.$emit("usuarioLogeado" , this.email);
    },
    confirmarResgistroSesion(){
      let registrar = true;

      for (let i = 0; i < this.listaClientes.length; i++) {
        if (this.listaClientes[i].email === this.email) {
            this.errorMessage = "Este correo ya tiene una cuenta";
            this.registro = false,
            this.titulo = "Iniciar sesión";
            this.nombre = "";
            this.apellidos = "";
            this.telefono ="";
            this.password = "";
            this.password2 = "";
            registrar = false;
        }
      }

      if (this.password !== this.password2) {
        this.errorMessage = "No coinciden las contraseñas";
        this.password = "";
        this.password2 ="";
        registrar = false;
      }

      if (registrar) {
        let cliente = {
          nombre: this.nombre,
          apellidos: this.apellidos,
          telefono: this.telefono,
          email: this.email,
          password: this.password
        }
        
        fetch('http://localhost:8080/tiendaTattoos/clientes', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify(cliente)
        })
        .then(response => response.json())
        .then(data => console.log('Success:', data))
        .catch((error) => console.error('Error:', error));

        this.registro = false,
        this.titulo = "Iniciar sesión";
        this.nombre = "";
        this.apellidos = "";
        this.telefono ="";
        this.password2 = "";
        this.errorMessage = "Resgistrado Correctamente Inicie sesión";
        this.listarClientes();
      }
    },
    listarClientes() {
      fetch('http://localhost:8080/tiendaTattoos/clientes')
        .then(response => response.json())
        .then(json => this.listaClientes = json);
        this.errorMessage = "";
    },
  },
  created(){
       this.listarClientes();
  }
};
</script>
  
<style scoped>
.formulario {
  display: flex;
  flex-direction: column;
  text-align: center;
  margin: 5% auto 17.9%;
  width: 30%;
  border-radius: 18px;
}
.contenido{
  width: 80%;
  margin: auto;
  background-color: rgb(248, 212, 197);
}
.sicuenta{
  cursor: pointer;
}

.formulario input, select, textarea{
  background-color: rgb(245, 193, 178);
  border: 1px solid rgb(248, 174, 154);
}

.form-group {
  margin-bottom: 15px;
}

.form-control {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.btn {
  padding: 3% 5%;
  margin: 5%;
  color: white;
  background-color: rgba(51, 51, 51, 0.8);
  transition: 1s;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.btn:hover{
  transition: 1s;
  background-color: #fd5437;
}

.error-message {
  background-color: red;
  margin-top: 10px;
}

@media screen and (max-width: 1024px) {
  .formulario {
    margin: 5% auto 35.5%;
    width: 40%;
  }
  .contenido{
    padding: 2%;
  }
}

</style>