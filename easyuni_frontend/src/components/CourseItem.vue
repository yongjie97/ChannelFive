<template>
    <router-link :to=url>
        <div class="card mb-2">
            <div class="card-body py-3 px-4">
                <h5 class="mb-1">{{ courseName }}</h5>
                <p class="small mb-0 text-muted">{{ schoolName }}
                </p>
            </div>
        </div>
    </router-link>
</template>
  
<script>

export default {
    name: 'CourseItem',
    props: {
        courseName: String,
        schoolName: String
    },
    data() {
        return {
            url: ''
        }
    },
    computed: {
        url() {
            return this.$route.params.uniName + "/" + this.courseName.replaceAll(' ', '-').toLowerCase()
        },
    }
}
</script>
  
<style scoped>
a {
    color: inherit;
    font-family: inherit;
    font-style: inherit;
    font-weight: inherit;
    text-decoration: none;
}

.card {
    border-radius: 0;
}

.col-bg {
    background-color: #ffc107;
}

.card:hover {
    background-color: #dfdfdf !important;
}
</style>