<template>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
    <router-link :to=url>
        <div class="card mb-2">
            <div class="card-body py-3 px-4">
                <h5 class="mb-1">{{ courseName }}</h5>
                <p class="small mb-0 text-muted">{{ universityName }}<span class="mx-2">|</span>{{ schoolName }}
                </p>
            </div>
        </div>
    </router-link>
</template>
  
<script>

export default {
    name: 'SearchItem',
    props: {
        courseName: String,
        schoolName: String,
        universityName: String,
    },
    computed: {
        url() {
            return '/university/' + this.universityName.replaceAll(' ', '-') + "/" + this.courseName.replaceAll(' ', '-').toLowerCase()
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
    animation: pulse;
    animation-duration: 1.5s;
}
</style>