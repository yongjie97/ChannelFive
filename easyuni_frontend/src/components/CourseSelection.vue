<template>
    <section class="service-wrapper py-2" id="course">
        <div class="container-fluid">
            <div class="row">
                <h2 class="h2 text-center col-12 py-5 semi-bold-600">{{ title }}</h2>
            </div>
        </div>
    </section>

    <section class="container overflow-hidden">
        <div class="row mb-5">
            <div class="input-group">
                <input @keyup.enter="submit()" type="search" v-model="query" class="form-control p-3"
                    placeholder="e.g. Computer Science" aria-label="Search" aria-describedby="search-addon" />
                <button @click.prevent="submit()" type="button" class="btn btn-warning btn-block text-white">Search</button>
            </div>
        </div>

        <div class="row gx-5 gx-sm-3 gx-lg-5 gy-lg-5 gy-3 pb-5 projects">
            <!-- Start Recent Work -->
            <div class="col-xl-3 col-md-4 col-sm-6 project ui branding">
                <router-link to="/university/national-university-of-singapore"
                    class="service-work card border-0 text-white shadow-sm overflow-hidden mx-5 m-sm-0">
                    <img class="service card-img" src="../assets/image/nus.png" alt="NUS">
                    <div class="service-work-vertical card-img-overlay d-flex align-items-end">
                        <div class="service-work-content text-left text-light">
                            <span class="btn btn-carousel btn-outline-light rounded-pill mb-lg-3 px-lg-4 light-300">NUS</span>
                            <p class="card-text">National University of Singapore</p>
                        </div>
                    </div>
                </router-link>
            </div><!-- End Recent Work -->

            <!-- Start Recent Work -->
            <div class="col-xl-3 col-md-4 col-sm-6 project ui graphic">
                <router-link to="/university/nanyang-technological-university"
                    class="service-work card border-0 text-white shadow-sm overflow-hidden mx-5 m-sm-0">
                    <img class="card-img" src="../assets/image/ntu.jpeg" alt="NTU">
                    <div class="service-work-vertical card-img-overlay d-flex align-items-end">
                        <div class="service-work-content text-left text-light">
                            <span class="btn btn-carousel btn-outline-light rounded-pill mb-lg-3 px-lg-4 light-300">NTU</span>
                            <p class="card-text">Nanyang Technological University</p>
                        </div>
                    </div>
                </router-link>
            </div><!-- End Recent Work -->

            <!-- Start Recent Work -->
            <div class="col-xl-3 col-md-4 col-sm-6 project branding">
                <router-link to="/university/singapore-management-university"
                    class="service-work card border-0 text-white shadow-sm overflow-hidden mx-5 m-sm-0">
                    <img class="card-img" src="../assets/image/smu.jpeg" alt="SMU">
                    <div class="service-work-vertical card-img-overlay d-flex align-items-end">
                        <div class="service-work-content text-left text-light">
                            <span class="btn btn-carousel btn-outline-light rounded-pill mb-lg-3 px-lg-4 light-300">SMU</span>
                            <p class="card-text">Singapore Management University</p>
                        </div>
                    </div>
                </router-link>
            </div>
        </div>
    </section>
</template>

<script>
export default {
    name: 'CourseSelection',
    data() {
        return {
            search: ''
        }
    },
    props: {
        title: String
    },
    methods: {
        submit: function () {
            if (this.query)
                this.$router.push("/search/" + this.query)
        }
    },
}
</script>

<style scoped src="../assets/style/templatemo.css">

</style>

<style scoped>
.btn-carousel:hover {
    background-color: #fff !important;
}
</style>