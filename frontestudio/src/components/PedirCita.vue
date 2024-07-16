<template>
    <div class="divformulario">
        <h1 class="tituloPag">Formulario Citas</h1>
        <div class="formulario">
            <form @submit.prevent="pedirCita()">
                <div class="info">
                    <div class="infoclient">
                        <h2>Información cliente</h2>
                        <label for="nombrec">Nombre: </label><br>
                        <input type="text" name="nombrec" id="nombrec" v-model="nombrec" required> <br><br>
                        <label for="apellidos">Apellidos: </label><br>
                        <input type="text" name="apellidos" id="apellidos" v-model="apellidos" required> <br><br>
                        <label for="telefono">Teléfono: </label><br>
                        <input type="tel" name="telefono" id="telefono" v-model="telefono" required> <br><br>
                        <label for="email">Correo electronico: </label><br>
                        <input type="email" name="email" id="email" v-model="email" required> <br><br>
                    </div>
                    <div class="infoTatus">
                        <h2>Información Tatuaje</h2>
                        <label for="descrip">Descripción del tatuaje: </label><br>
                        <textarea name="descrip" id="descrip" cols="33" rows="3" v-model="descrip" required placeholder="Un dragon en el hombro/ una flor en la muñeca"></textarea> <br><br>
                        <label for="color">Color:</label><br>
                        <input type="checkbox" name="color" id="color" v-model="color" :on-change="calcularPrecio()"> <br><br>
                        <label for="tamano">Tamaño:</label><br>
                        <input type="number" required v-model="tamano" placeholder="ejem: 5.2" :on-change="calcularPrecio()" min="0.1"> <br><br>
                        <label for="precio">Precio Total:</label> <br>
                        <input type="text" name="precio" id="precio" v-model="precio" disabled>
                    </div>
                    <div class="infoCitas">
                        <h2>Información Cita</h2>
                        <label for="tatuador">Nombre del Tatudor:</label><br>
                        <select name="tatuador" id="tatuador" v-if="(nomTatuador == '')" v-model="nomTatuador" required>
                            <option value="" selected disabled>Seleccione una tatuador</option>
                            <option v-for="item in listaTatuadores" :key="item.idTatuadores" :value="item.nombre"> {{ item.nombre }}</option>
                        </select>  
                        <select name="tatuador" id="tatuador" v-if="(nomTatuador != '')" required>
                            <option :value="nomTatuador" selected >{{ nomTatuador }}</option>
                        </select> <br><br> 
            
                        <label for="fecha">Fecha de Cita:</label><br>
                        <input type="date" name="fecha" id="fecha" required v-model="fecha" :min="fechaHoy" :on-change="comprobarFecha()">
                    </div>
                </div>
                <button type="submit" class="botonCita">Pedir cita</button>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        props: ["nombreTatuadores"],
        data(){
            return{
                nomTatuador: "",
                fechaHoy: "",
                listaCitas:[],
                listaTatuadores:[],
                listaClientes:[],
                listaTatuajes:[],
                nombrec:"",
                apellidos: "",
                telefono: "",
                email: "",
                descrip: "",
                color: null,
                tamano: "",
                precio: 0,
                fecha: ""    

            }
        },
        /* reaccionar a los cambios en el nombreTatuadores y actualiza automaticamente */
        watch: {
            nombreTatuadores: function(newVal) {
                this.nomTatuador = newVal;
            }
        },
        methods:{
            rellenarListas() {
                fetch('http://localhost:8080/tiendaTattoos/citas')
                    .then(response => response.json())
                    .then(json => this.listaCitas = json);

                fetch('http://localhost:8080/tiendaTattoos/tatuadores')
                    .then(response => response.json())
                    .then(json => this.listaTatuadores = json);

                fetch('http://localhost:8080/tiendaTattoos/clientes')
                    .then(response => response.json())
                    .then(json => this.listaClientes = json);


                fetch('http://localhost:8080/tiendaTattoos/tatuajes')
                    .then(response => response.json())
                    .then(json => this.listaTatuajes = json);
            },
            calcularPrecio(){
                if(this.tamano == 0 || this.tamano === "") {
                    return;
                }

                var datos ={
                    color: this.color ? 1 : 0,
                    tamano: this.tamano
                };
                fetch('http://localhost:8080/tiendaTattoos/tatuajes/calcularPrecio', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify(datos) 
                }).then(response => {
                    if (!response.ok) {
                        throw new Error('Error en la respuesta del servidor: ' + response.statusText);
                    }
                    return response.text(); 
                })
                .then(data => {
                    this.precio = data;
                })
                .catch(error => {
                    console.error('Error al calcular el precio:', error);
                });
            },

            comprobarFecha(){
                for (let i = 0; i < this.listaCitas.length; i++) {
                    if (this.listaCitas[i].fecha === this.fecha) {
                        alert("Fecha no disponible. Seleccione otra porfavor");
                        this.fecha = "";
                        break;
                    }
                }
            },

            async pedirCita(){

                var nuevaCita = {
                    fecha: this.fecha,
                    activo: 1,
                    cliente: 0,
                    tatuador: 0,
                    tatuajes: 0
                }

                /*********************************** GESTIÓN DEL CLIENTE ***************************/

                let regis = false;

                this.listaClientes.forEach(element => {
                    if (element.email.toLowerCase() === this.email.toLowerCase()){
                        regis = true;
                        nuevaCita.cliente = element.idClientes
                    }
                });

                var cliente;

                if (regis){

                    let clienteSele = this.listaClientes[(nuevaCita.cliente - 1)];

                    cliente = {
                        idClientes: nuevaCita.cliente,
                        nombre: this.nombrec,
                        apellidos: this.apellidos,
                        telefono: this.telefono,
                        email: this.email,
                        password: clienteSele.password
                    }

                    await fetch('http://localhost:8080/tiendaTattoos/clientes', {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(cliente)
                    })
                    .then(response => response.text())  
                    .then(text => {
                        if (text === 'Cliente actualizado correctamente') {
                            console.log(text)
                        } else {
                            console.error('Unexpected response:', text);  
                        }
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                        alert("Error al editar el Cliente. Por favor, inténtelo de nuevo.");
                    });

                }else{

                    cliente = {
                        nombre: this.nombrec,
                        apellidos: this.apellidos,
                        telefono: this.telefono,
                        email: this.email,
                        password: prompt("Introduce una contraseña: ")
                    }

                    await fetch('http://localhost:8080/tiendaTattoos/clientes', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(cliente)
                    })
                    .then(response => response.text())  
                    .then(text => {
                        if (text === 'Cliente insertado correctamente') {
                            console.log(text)
                        } else {
                            console.error('Unexpected response:', text);  
                        }
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                        alert("Error al insertar el Cliente. Por favor, inténtelo de nuevo.");
                    });

                    this.rellenarListas();

                    let nuevoCliente;
                    await fetch('http://localhost:8080/tiendaTattoos/clientes/' + (this.listaClientes.length+1))
                    .then(response => response.json())
                    .then(json => nuevoCliente = json);

                    nuevaCita.cliente = nuevoCliente.idClientes;
                }

                this.nombrec = "";
                this.apellidos = "";
                this.telefono = "";
                this.email = "";
                regis = false;

                /*********************************** GESTIÓN DEL TATUAJE ***************************/

                var tatuaje = {
                    descripcion: this.descrip,
                    color: this.color ? 1 : 0,
                    tamano: this.tamano,
                    precio: this.precio,
                };

                await fetch('http://localhost:8080/tiendaTattoos/tatuajes', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(tatuaje)
                    })
                    .then(response => response.text())  
                    .then(text => {
                        if (text === 'Tatuaje insertado correctamente') {
                            console.log(text)
                        } else {
                            console.error('Unexpected response:', text);  
                        }
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                        alert("Error al insertar el Tatuaje. Por favor, inténtelo de nuevo.");
                    });

                this.rellenarListas();

                let nuevoTatu;
                await fetch('http://localhost:8080/tiendaTattoos/tatuajes/' + (this.listaTatuajes.length+1))
                .then(response => response.json())
                .then(json => nuevoTatu = json);
                nuevaCita.tatuajes = nuevoTatu.idTatuajes;

                this.descrip = "",
                this.color = null,
                this.tamano = "",
                this.precio = 0;
   
                if(this.nomTatuador === ""){
                    alert("Seleccione un tatuador");
                    return;
                }

                this.listaTatuadores.forEach(element => {
                    if (element.nombre.toLowerCase() === this.nomTatuador.toLowerCase()) {
                        nuevaCita.tatuador = element.idTatuadores;
                    }
                });

                this.fecha = "";
                this.nomTatuador = "";
                
                await fetch('http://localhost:8080/tiendaTattoos/citas', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(nuevaCita)
                    })
                    .then(response => response.text())  
                    .then(text => {
                        if (text === 'Cita insertada correctamente') {
                            console.log(text)
                            alert(text);
                        } else {
                            console.error('Unexpected response:', text);  
                        }
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                        alert("Error al insertar la Cita. Por favor, inténtelo de nuevo.");
                    });
                
                    this.rellenarListas();
            }
        },
        created(){
            this.rellenarListas();
            this.nomTatuador = this.nombreTatuadores;

            let nombreCliente = localStorage.getItem("nombre");
            if (nombreCliente != null){
                this.nombrec = localStorage.getItem("nombre");
                this.apellidos = localStorage.getItem("apellidos");
                this.telefono = localStorage.getItem("telefono");
                this.email = localStorage.getItem("email");
                this.password = localStorage.getItem("password");
            }

            let hoy = new Date();
            let dia = String(hoy.getDate()).padStart(2, '0');
            let mes = String(hoy.getMonth() + 1).padStart(2, '0');
            let año = hoy.getFullYear();
            this.fechaHoy = `${año}-${mes}-${dia}`;
        }
    }
</script>

<style>

.divformulario{
    display: flex;
    flex-direction: column;
    width: 70%;
    margin: 1% auto 3%;
}

.botonCita{
    cursor: pointer;
    margin: 2% auto;
    width: 80%;
    padding: 1% 5%;
    border: 0px;
    border-radius: 18px;
    color: white;
    font-size: larger;
    background-color: rgba(51, 51, 51, 0.8);
    transition: 1s;
}

.botonCita:hover{
    transition: 1s;
    background-color: #fc8b77;
}

.formulario{
    text-align: center;
    background-color: rgb(248, 212, 197);
    border-radius: 18px;
    display: flex;
    flex-direction: column;
}

.info{
    display: flex;
    flex-direction: row;
}

.info input, select, textarea{
    width: 60%;
    margin: 1%;
    background-color: rgb(245, 193, 178);
    border: 1px solid rgb(248, 174, 154);
}

.infoclient, .infoTatus, .infoCitas{
    width: 33%;
    align-self: center;
}

@media screen and (max-width: 768px) {
    .info{
        flex-direction: column;
    }
    .infoclient{
        width: 100%;
    }
    .infoCitas{
        width: 100%;
        margin-bottom: 5%;
    }
    .infoTatus{
        width: 100%;
    }
}

@media screen and (max-width: 480px) {
  
}

</style>