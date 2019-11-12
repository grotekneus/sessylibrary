<template>
    <div>
        <h3>Boek detail: {{ isbn }}</h3>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                isbn: this.$route.params.isbn
            }
        },
        name: 'boekdetail'
    }
</script>

<style>

</style>