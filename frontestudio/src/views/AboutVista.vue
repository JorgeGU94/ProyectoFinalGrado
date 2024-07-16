<template>
  <div class="vistaSobre">
    <h1 class="tituloPag">SOBRE NOSOTROS</h1>
    <div class="contenedorSobre">
      <div class="textoSobre">
        <p>
          Bienvenido a DrawTattoo, el estudio de tatuajes donde el arte cobra vida en tu piel. Ubicados en el corazón de la ciudad, nos especializamos en ofrecer una experiencia única y personalizada a cada uno de nuestros clientes, combinando creatividad, profesionalismo y un profundo respeto por el arte del tatuaje.
        </p>
        <h2>Nuestra Filosofía</h2>
        <p>
          En DrawTattoo, creemos que un tatuaje es mucho más que una imagen en la piel; es una forma de expresión personal, una historia que se lleva consigo para siempre. Nos dedicamos a entender tus ideas y convertirlas en diseños que no solo sean visualmente impresionantes, sino que también tengan un profundo significado personal para ti.
        </p>
        <h2>Nuestro Equipo</h2>
        <p>
          Contamos con un equipo de tatuadores altamente cualificados, cada uno con su propia especialidad y estilo único. Desde detallados trabajos de realismo hasta vibrantes piezas de acuarela y audaces diseños de Blackwork, nuestros artistas están aquí para hacer realidad tu visión. Nos enorgullece presentar a algunos de nuestros destacados tatuadores:
        </p>
        <p>
          <b>Ana Rodríguez:</b> Especialista en acuarela, Ana transforma cada tatuaje en una obra de arte llena de color y vida. <br>
          <b>Carlos Gómez:</b> Maestro del Blackwork, Carlos crea impactantes tatuajes utilizando contrastes profundos y detalles precisos. <br>
          <b>Sofía Hernández:</b> Experta en realismo, Sofía captura la esencia de cada sujeto con una precisión sorprendente. <br>
        </p>
        <h2>Nuestra Galería</h2>
        <p>
          En nuestra Galería, puedes explorar una amplia colección de tatuajes realizados por nuestros artistas. Esta galería es una fuente de inspiración para aquellos que buscan su próximo tatuaje. Desde diseños minimalistas hasta elaboradas piezas de cuerpo completo, encontrarás una variedad de estilos que muestran la versatilidad y el talento de nuestro equipo.
        </p>
        <h2>Compromiso con la Calidad y la Seguridad</h2>
        <p>
          La seguridad y la higiene son nuestras máximas prioridades. Utilizamos equipos de última generación y seguimos estrictos protocolos de limpieza para garantizar un entorno seguro y estéril. Todos nuestros materiales son de la más alta calidad y desechables, y nuestros artistas están certificados y continuamente actualizados en las mejores prácticas de la industria.
        </p>
        <h2>Testimonios de Clientes</h2>
        <p>
          La satisfacción de nuestros clientes es nuestro principal objetivo. Nos enorgullece recibir comentarios positivos y recomendaciones de aquellos que han confiado en nosotros para sus tatuajes. Aquí hay algunas palabras de nuestros clientes satisfechos:
        <br><br>
        "¡DrawTattoo superó todas mis expectativas! Ana transformó mi idea en una hermosa pieza de arte en mi brazo." <b> - María L. </b><br>
        "Carlos es un genio del Blackwork. Su atención al detalle es asombrosa." <b> - Juan P. </b> <br>
        "Sofía hizo un retrato de mi perro que parece una fotografía. ¡Increíble!" <b> - Laura G. </b><br></p>
        <h2>Visítanos</h2>
        <p>
        Te invitamos a visitar nuestro estudio para una consulta gratuita. Nuestro equipo estará encantado de ayudarte a planificar tu próximo tatuaje, responder a tus preguntas y proporcionarte toda la información que necesites. En DrawTattoo, cada tatuaje es una colaboración, y estamos aquí para asegurarnos de que tu experiencia sea memorable y satisfactoria.

        Para más información, reservas o consultas, no dudes en contactarnos o visitarnos en nuestras redes sociales. ¡Esperamos verte pronto y ser parte de tu viaje en el mundo del tatuaje!

        DrawTattoo - Donde el arte y la pasión se encuentran.</p>
      </div>
    </div>
    <div>
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
          nombreTatuadores: ""
        }
      }
}
</script>

<style>

.contenedorSobre{
  text-align: justify;
  background-color: rgba(51, 51, 51, 0.8);
  font-size: large;
}

.textoSobre{
  width: 70%;
  padding: 2%;
  margin: auto;
  background-color: rgba(248, 212, 197, 0.7);
}

.textoSobre h2{
  text-align: center;
  margin: 2% auto;
}

@media screen and (max-width: 768px) {
  .textoSobre{
    padding: 1% 6% 2%;
    text-align: justify;
    font-size: 20px;
  }
  
}

@media screen and (max-width: 480px) {
  .textoSobre{
    text-align: center;
  }
}

</style>