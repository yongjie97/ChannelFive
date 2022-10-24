<template>
    <div class="min-vh-100" style="background-color: #E0E0E0;">
        <div class="uni-background mb-5 py-5 img-fluid">
            <div class="container text-center py-5">
                <h3 class="text-white display-3 py-5 text-capitalize">Search Results</h3>
                <div class="mx-auto" style="width: 100%; max-width: 600px;">
                </div>
            </div>
        </div>
        <div class="container py-4">
            <div class="row mb-5 justify-content-center">
                <div class="col col-xl-10">
                    <div class="input-group">
                        <input type="search" v-model="query" class="form-control p-2"
                            placeholder="e.g. Computer Science" aria-label="Search" aria-describedby="search-addon" />
                        <button @click.prevent="submit()" type="button"
                            class="btn btn-warning text-white">Search</button>
                    </div>
                </div>
            </div>

            <div class="row d-flex justify-content-center" v-if="uniData.length===0">
                <div class="col col-xl-10">
                    <h5 style="text-align: center">No course found. Try searching for another course.</h5>
                </div>
            </div>

            <div class="row d-flex justify-content-center mb-5">
                <div class="col col-xl-10">
                    <SearchItem v-for="(data, index) in uniData" :courseName=data.degree :schoolName=data.school
                        :universityName="data.university" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import SearchItem from '../components/SearchItem.vue'

export default {
    name: 'University',
    components: {
        SearchItem,
    },
    data() {
        return {
            query: '',
            uniData: [],
        }
    },
    mounted() {
        if (this.$route.params.query)
            this.query = this.$route.params.query
        axios({
            method: 'get',
            url: 'https://data.gov.sg/api/action/datastore_search?resource_id=9326ca53-9153-4a9c-b93f-8ae032637b70&q=2019&limit=100'
        })
            .then(response => {
                if (response != null) {
                    response.data.result.records.forEach(element => {
                        if (element.university !== "Singapore Institute of Technology")
                            if (element.degree.toUpperCase().includes(this.$route.params.query.toUpperCase()))
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
    background: linear-gradient(rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.8)), url(./../assets/image/search.jpg);
    background-position: center;
}
</style>
