<template>
</template>
    
<script>
export default {
    name: 'AccountVerification',
    mounted() {
        axios({
            method: 'get',
            url: "http://localhost:8080/register/verify/" + this.$route.params.email + "/" + this.$route.params.verification_code
        })
            .then(response => {
                if (response != null) {
                    if (!response.data.success)
                        this.$router.push('/')
                    else {
                        this.$router.push({ name: 'Login', query: { message: response.data.message }})
                    }
                }
            }).catch((error) => {
                this.$router.push('/')
            })
    }
}
</script>