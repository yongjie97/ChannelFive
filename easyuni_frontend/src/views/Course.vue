<template>
    <div class="min-vh-100 pt-5" style="background-color: #E0E0E0;">
        <CourseSelection :title="'Looking for Courses?'" />
    </div>
</template>

<script>
import CourseSelection from '../components/CourseSelection.vue'

export default {
    name: 'Course',
    components: {
        CourseSelection
    },
}
</script>