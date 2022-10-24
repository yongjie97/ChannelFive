<template>
    <div class="min-vh-100" style="background-color: #E0E0E0;">
        <div class="uni-background mb-5 py-5 img-fluid">
            <div class="container text-center py-5">
                <h3 class="text-white display-3 py-5 text-capitalize">{{ uniName }}</h3>
                <div class="mx-auto" style="width: 100%; max-width: 600px;">
                </div>
            </div>
        </div>
        <div class="container py-5">
            <div class="row d-flex justify-content-center">
                <div class="col col-xl-10">
                    <CourseItem v-for="(data, index) in uniData" :courseName=data.degree :schoolName=data.school />
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import CourseItem from '../components/CourseItem.vue'

export default {
    name: 'University',
    components: {
        CourseItem,
    },
    data() {
        return {
            uniName: '',
            uniData: []
        }
    },
    mounted() {
        this.uniName = this.$route.params.uniName.replaceAll('-', ' ');
        axios({
            method: 'get',
            url: 'https://data.gov.sg/api/action/datastore_search?resource_id=9326ca53-9153-4a9c-b93f-8ae032637b70&q=2019&limit=500'
        })
            .then(response => {
                if (response != null) {
                    response.data.result.records.forEach(element => {
                        if (element.university.toUpperCase() === this.$route.params.uniName.replaceAll('-', ' ').toUpperCase())
                            if (!element.degree.includes("*") && !element.degree.includes("#") && !element.degree.includes("^")
                                && !element.degree.includes("Cum Laude"))
                                this.uniData.push(element);
                    });

                }
            }).catch((error) => {
                alert(error.message)
            })
    },
}
</script>

<style scoped>
.uni-background {
    background: linear-gradient(rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.8)), url(./../assets/image/university.jpg);
    background-position: center;
}
</style>
