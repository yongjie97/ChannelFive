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
                <div class="col col-xl-10 mb-5">
                    <div class="input-group">
                        <input @keyup.enter="submit()" type="search" v-model="query" class="form-control p-3" 
                            placeholder="e.g. Computer Science" aria-label="Search" aria-describedby="search-addon" />
                        <button @click.prevent="submit()" type="button"
                            class="btn btn-warning text-white">Search</button>
                    </div>
                </div>
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
            uniData: [],
        }
    },
    mounted() {
        this.uniName = this.$route.params.uniName.replaceAll('-', ' ');
        axios({
            method: 'get',
            url: 'https://data.gov.sg/api/action/datastore_search?resource_id=9326ca53-9153-4a9c-b93f-8ae032637b70&limit=847'
        })
            .then(response => {
                if (response != null) {
                    response.data.result.records.forEach(element => {
                        if (element.year === "2019")
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
    methods: {
        submit: function () {
            this.$router.push("/search/" + this.query)
            axios({
                method: 'get',
                url: 'https://data.gov.sg/api/action/datastore_search?resource_id=9326ca53-9153-4a9c-b93f-8ae032637b70&q=2019&limit=100'
            })
                .then(response => {
                    if (response != null) {
                        this.uniData = [];
                        response.data.result.records.forEach(element => {
                            if (element.university !== "Singapore Institute of Technology")
                                if (element.degree.toUpperCase().includes(this.query.toUpperCase()))
                                    if (!element.degree.includes("*") && !element.degree.includes("#") && !element.degree.includes("^")
                                        && !element.degree.includes("Cum Laude"))
                                        this.uniData.push(element);
                        });
                    }
                }).catch((error) => {
                    alert(error.message)
                })
        }
    },
}
</script>

<style scoped>
.uni-background {
    background: linear-gradient(rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.8)), url(./../assets/image/university.jpg);
    background-position: center;
}
</style>
