<template>
  <CarouselSlide />
  <Search />
</template>
  
  <script>
  import CarouselSlide from '../components/CarouselSlide.vue'
  import Search from '../components/Search.vue'
  
  export default {
    name: 'Home',
    components: {
      CarouselSlide,
      Search
    }
  }
  </script>