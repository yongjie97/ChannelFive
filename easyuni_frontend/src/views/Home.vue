<template>
  <CarouselSlide />
  <CourseSelection :title="'Courses by University'" />
</template>
  
  <script>
  import CarouselSlide from '../components/CarouselSlide.vue'
  import CourseSelection from '../components/CourseSelection.vue'
  
  export default {
    name: 'Home',
    components: {
      CarouselSlide,
      CourseSelection
    }
  }
  </script>