<template>
    <div id="containerUser">
        <h1 class="tituloPag">Zona de User</h1>
        <div class="botonLogout">
            <button @click="$emit('logout')">Log out</button>
        </div>
        <div class="fondoCitas">
            <div class="listadoCitas">
                <h2>Citas del User</h2>
                <table>
                    <tr>
                        <th>Id Cita</th>
                        <th>Fecha</th>
                        <th>Nombre</th>
                        <th>Tatuador</th>
                        <th>Descripción</th>
                        <th>Tamaño</th>
                        <th>Precio</th>
                        <th>Editar Cita</th>
                    </tr>
                    <tr class="citas" v-for="(item, index) in listaMostrar" :key="index">
                        <td> {{ item.idCitas }} </td>
                        <td> {{ item.fecha }} </td>
                        <td> {{ item.cliente.nombre }} </td>
                        <td> {{ item.tatuador.nombre  }} </td>
                        <td> {{ item.tatuajes.descripcion }} </td>
                        <td> {{ item.tatuajes.tamano }} cm</td>
                        <td> {{ item.tatuajes.precio }} €</td>
                        <td v-if="item.fecha > fechaHoy"> <button type="button" @click="mostrarFormuEdit(item)" >Editar</button> </td>
                    </tr>
                </table>
                <div v-if="boolean" class="formularioEditCita">
                    <label for="fecha">Nueva Fecha:</label><br>
                    <input type="date" name="fecha" id="fecha" required v-model="fecha" :min="fechaHoy" @change="comprobarFecha()"> <br><br>

                    <label for="tatuador">Tatuador:</label> <br>
                    <select name="tatuador" id="tatuador" v-model="tatuador" required>
                        <option v-for="item in listaTatuadores" :key="item.idTatuadores" :value="item.nombre"> {{ item.nombre }}</option>
                    </select> <br><br>

                    <label for="descripcion">Descripción:</label><br>
                    <textarea name="descrip" id="descrip" cols="33" rows="3" v-model="descrip" required placeholder="Un dragon en el hombro/ una flor en la muñeca"></textarea> <br><br>
                    <label for="tamano">Tamaño:</label><br>
                    <input type="number" required v-model="tamano" placeholder="ejem: 5.2" min="0"> <br><br>
                    <label for="color">Color:</label><br>
                    <input type="checkbox" name="color" id="color" v-model="color" > <br><br>
                    <button type="button" class="editCita" @click="editarCita()">Editar Cita</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
      props: {},
      data() {
        return { 
            cliente: [],
            listaCitas: [],
            listaMostrar: [],
            listaTatuadores: [],
            email:"",
            fecha:"",
            tatuador: "",
            tamano: "",
            color: null,
            fechaHoy:"",
            descrip: "",
            CitaSelec: "",
            boolean: false,
        }
        },
        methods: {
            mostrarFormuEdit(item){
                this.boolean = true;             
                this.CitaSelec = item;
                this.fecha = item.fecha;
                this.tatuador = item.tatuador.nombre;
                this.tamano = item.tatuajes.tamano;
                this.color = (item.tatuajes.color == 1) ? true : false;
                this.color = item.tatuajes.color;
                this.descrip = item.tatuajes.descripcion;
            },
            async rellenarListaCitas(){

                await fetch('http://localhost:8080/tiendaTattoos/tatuadores')
                    .then(response => response.json())
                    .then(json => this.listaTatuadores = json);

                let urlClie = "http://localhost:8080/tiendaTattoos/clientes?id=&nombre=&apellidos=&telefono=&email=" + this.email + "&pass=";
                await fetch(urlClie)
                .then(response => response.json())
                .then(json => this.cliente = json);

                this.cliente = this.cliente[0];
                if (this.cliente) {
                    localStorage.setItem("nombre", this.cliente.nombre);
                    localStorage.setItem("apellidos", this.cliente.apellidos);
                    localStorage.setItem("telefono", this.cliente.telefono);
                    localStorage.setItem("email", this.cliente.email);
                    localStorage.setItem("password", this.cliente.password);
                

                    let urlCita = "http://localhost:8080/tiendaTattoos/citas?idCitas=&fecha=&activo=&cliente=" + (this.cliente.idClientes) + "&tatuador=&tatuajes="

                    await fetch(urlCita)
                        .then(response => response.json())
                        .then(json => this.listaCitas = json);

                    
                    for (let i = 0; i < this.listaCitas.length; i++){

                        
                        let listaTatuadores = [] 
                        let listaTatuajes = [];

                        await fetch('http://localhost:8080/tiendaTattoos/tatuadores/' + this.listaCitas[i].tatuador)
                            .then(response => response.json())
                            .then(json => {
                                listaTatuadores.push(json);
                            });

                        

                        await fetch('http://localhost:8080/tiendaTattoos/tatuajes/' + this.listaCitas[i].tatuajes)
                            .then(response => response.json())
                            .then(json => {
                                listaTatuajes.push(json);
                            });
            
                        this.listaMostrar.push({
                                idCitas : this.listaCitas[i].idCitas,
                                fecha : this.listaCitas[i].fecha,
                                cliente : this.cliente,
                                tatuador : listaTatuadores[0],
                                tatuajes : listaTatuajes[0]
                        })
                    }
                }
            },
            editarCita(){

                this.CitaSelec.fecha = this.fecha;

                for (let i = 0; i < this.listaTatuadores.length; i++) {
                    if (this.listaTatuadores[i].nombre === this.tatuador) {
                        this.CitaSelec.tatuador = this.listaTatuadores[i];
                        break;
                    }
                }

                this.CitaSelec.tatuajes.descripcion = this.descrip;
                this.CitaSelec.tatuajes.tamano = this.tamano;
                this.CitaSelec.tatuajes.color = (this.color == true) ? 1 : 0;
                this.CitaSelec.activo = 1;

                fetch('http://localhost:8080/tiendaTattoos/citas', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(this.CitaSelec)
                })
                .then(response => response.text())  
                .then(text => {
                    if (text === 'Cita actualizada correctamente') {
                        alert("Cita editada correctamente, Muchas gracias.");
                    } else {
                        console.error('Unexpected response:', text);  
                        alert("Error al editar la cita. Por favor, inténtelo de nuevo.");
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                    alert("Error al editar la cita. Por favor, inténtelo de nuevo.");
                });

                this.cliente = [],
                this.listaCitas= [],
                this.listaMostrar= [],
                this.listaTatuadores= [],
                this.fecha="",
                this.tatuador= "",
                this.tamano= "",
                this.color= null,
                this.descrip= "",
                this.CitaSelec= "",
                this.boolean= false,
                this.rellenarListaCitas();
            },
            comprobarFecha(){
                if(this.boolean) {
                    for (let i = 0; i < this.listaCitas.length; i++) {
                        if (this.listaCitas[i].fecha === this.fecha) {
                            alert("Fecha no disponible. Seleccione otra porfavor");
                            this.fecha = "";
                            break;
                        }
                    }
                }else{
                    this.boolean = true;
                }
            }
        },   
        created() {
            this.email = this.$route.params.email;
            this.rellenarListaCitas();
            let hoy = new Date();
            let dia = String(hoy.getDate()).padStart(2, '0');
            let mes = String(hoy.getMonth() + 1).padStart(2, '0');
            let año = hoy.getFullYear();
            this.fechaHoy = `${año}-${mes}-${dia}`;
        }
}
</script>

<style>

.fondoCitas{
    background-color: rgba(51, 51, 51, 0.8);
    margin-bottom: 21%;
}

.listadoCitas{
    width: 70%;
    background-color: rgba(248, 212, 197, 0.8);
    margin: auto;
    padding: 2%;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.listadoCitas h2{
    text-align: center;
}

.botonLogout{
    text-align: center;
    height: 5%;
    margin-bottom: 2%;
}

.botonLogout button {
  padding: 1% 4%;
  color: white;
  background-color: rgba(51, 51, 51, 0.8);
  transition: 1s;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.botonLogout button:hover{
  transition: 1s;
  background-color: #fd5437;
}

table{
    width: 80%;
}

table,th,tr,td{
    text-align: center;
    border: 1px solid black;
    border-collapse: collapse;
}

th{
    background-color: rgba(51, 51, 51, 0.7);
}

th,td{
    padding: 1%;
}

.formularioEditCita{
    text-align: center;
    width: 50%;
    margin: 2% auto;
}

.formularioEditCita input, select, textarea{
    text-align: center;
    width: 60%;
    margin: 1%;
    background-color: rgb(245, 193, 178);
    border: 1px solid rgb(248, 174, 154);
}

.editCita{
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

.editCita:hover{
    transition: 1s;
    background-color: #fc8b77;
}

</style>