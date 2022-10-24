<template>
  <CarouselSlide />
  <UniversityItem />
</template>
  
  <script>
  import CarouselSlide from '../components/CarouselSlide.vue'
  import UniversityItem from '../components/UniversityItem.vue'
  
  export default {
    name: 'Home',
    components: {
      CarouselSlide,
      UniversityItem
    }
  }
  </script>