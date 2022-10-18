<template>
  <NavigationBar/>
  <router-view></router-view>
  <Footer/>
</template>

<script>
import NavigationBar from './components/NavigationBar.vue'
import Footer from './components/Footer.vue'

export default {
  name: 'App',
  components: {
    NavigationBar,
    Footer
  }
}
</script>

<style>

    .btn:hover {
        background-color: #edba22;
    }

    .btn:active {
        background-color: #f7c93f;
    }

    .form-control:focus {
        border-color: #f0ad4e;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(237,186,34,.5);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(237,186,34,.5);
    }

</style>