<template>
    <div class="banner-wrapper bg-light">
        <div id="index_banner" class="banner-vertical-center-index container-fluid pt-5">

            <!-- Start slider -->
            <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">

                        <div class="py-5 row d-flex align-items-center">
                            <div class="banner-content col-lg-8 col-8 offset-2 m-lg-auto text-left py-5 pb-5">
                                <h1
                                    class="banner-heading h1 text-warning display-3 mb-0 pb-5 mx-0 px-0 light-300 typo-space-line">
                                    <strong>Welcome to EasyUni!</strong>
                                </h1>

                                <p class="banner-body text-light py-3 mx-0 px-0">View all the latest median salary and
                                    indicative grade profile across all
                                    <strong>universities</strong> in Singapore. Feeling unsure about your university
                                    prospect? Use our discussion board features to clarify your doubts.
                                </p>
                                <router-link to="/course"
                                    class="banner-button btn rounded-pill btn-outline-warning btn-lg px-4" href=""
                                    role="button">Browse Courses</router-link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End slider -->

        </div>
    </div>
    <!-- End Banner Hero -->
</template>

<script>
export default {
    name: 'CarouselSlide'
};
</script>

<style scoped src="../assets/style/templatemo.css">

</style>