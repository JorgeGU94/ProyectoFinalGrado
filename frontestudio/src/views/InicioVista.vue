<template>
  <div id="contenerdorInicio">
    <div id="imagenLogo">
      <img src="../assets/logo.png" alt="logo">
    </div>
    <div id="texto"> 
      <div>
        <h2 class="bienvenida">¡Bienvenidos a DrawTattoo!</h2>
        <p>
          Estamos encantados de que hayas decidido visitar nuestra página web. En DrawTattoo, nos apasiona el arte del tatuaje y nos dedicamos a ofrecerte las mejores experiencias y servicios en el mundo del tatuaje. Permítenos guiarte a través de los distintos sitios de nuestra página web para que puedas explorar todo lo que tenemos para ofrecer.
        </p>
      </div>
      <div>
        <h2 class="titulo">
          Galería
        </h2>
        <p>
          Nuestra Galería es el lugar perfecto para inspirarte. Aquí podrás ver una extensa colección de tatuajes realizados por nuestros talentosos artistas. Desde intrincados diseños en blanco y negro hasta vibrantes piezas a todo color, nuestra galería te muestra la diversidad y la calidad de nuestro trabajo. Navega por nuestras categorías y encuentra el estilo que más te atraiga.
        </p>
      </div>
      <div>
        <h2 class="titulo">
          Tatuadores
        </h2>
        <p>
          En la sección de Tatuadores, te presentamos a los artistas que forman parte de la familia DrawTattoo. Cada uno de nuestros tatuadores tiene su propia especialidad y estilo único. Aquí podrás conocer más sobre ellos, ver ejemplos de sus trabajos anteriores y descubrir qué tipo de tatuaje es su especialidad. Ya sea que busques realismo, tradicional, geométrico, o cualquier otro estilo, tenemos al tatuador perfecto para ti.
        </p>
      </div>
      <div>
        <h2 class="titulo">
          Sobre Nosotros
        </h2>
        <p>
          En la sección de Más Información, podrás aprender más sobre nosotros y nuestra filosofía. Nos esforzamos por crear un ambiente acogedor y profesional, donde cada cliente se sienta cómodo y seguro. Aquí encontrarás detalles sobre nuestras políticas de higiene y seguridad, la historia de nuestro estudio, y testimonios de clientes satisfechos. Además, ofrecemos respuestas a las preguntas más frecuentes para que tengas toda la información que necesitas antes de tomar la decisión de tatuarte con nosotros.
        </p>
      </div>
      <div>
        <p>
          Gracias por visitar DrawTattoo. Esperamos que disfrutes explorando nuestra página web y que encuentres la inspiración y el tatuador perfecto para tu próximo tatuaje. Si tienes alguna pregunta o necesitas más información, no dudes en contactarnos. ¡Estamos aquí para ayudarte a llevar tu visión artística a la realidad!
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>

#contenerdorInicio{
  display: flex;
  flex-direction: column;
  text-align: center;
  color: rgb(255, 255, 255);
  padding: 5% 0.01% 2%;
}

#imagenLogo img{
  border-radius: 50%;
  width: 15%;
}

h1 {
  font-size: 10em;
  margin-top: 0;
}

#texto{
  margin: 1% auto 2.5%;
  width: 70%;
  color: black;
  background-color: rgb(248, 212, 197, 0.9);
  border-radius: 18px;
  
}

#texto h2{
  margin-top: 2%;
  padding: 1%;
}

.bienvenida{
  font-size: 30px;
}

#texto p{
  padding: 1% 5% 2%;
  text-align: justify;
  font-size: 20px;
}

@media screen and (max-width: 768px) {
  #imagenLogo img{
    border-radius: 50%;
    width: 25%;
  }

  #texto h2{
    margin-top: 4%;
    padding: 1%;
  }

  #texto p{
    padding: 1% 6% 2%;
    text-align: justify;
    font-size: 20px;
  }
}

@media screen and (max-width: 480px) {
  #imagenLogo img{
    width: 40%;
  }
  #texto p{
    text-align: center;
  }
}

</style>