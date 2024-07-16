import { createRouter, createWebHashHistory } from 'vue-router'

import Inicio from '../views/InicioVista.vue';
import Galeria from '../views/GaleriaVista.vue';
import Tatuadores from '../views/TatuadoresVista.vue';
import About from '../views/AboutVista.vue';
import Login from '../components/LoginComponent.vue';
import User from '../views/UserVista.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: Inicio
  },
  {
    path: '/galeria',
    name: 'Galeria',
    component: Galeria
  },
  {
    path: '/tatuadores',
    name: 'Tatuadores',
    component: Tatuadores
  },
  {
    path: '/nosotros',
    name: 'Nosotros',
    component: About
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/user/:email',
    name: 'User',
    component: User
  }
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
