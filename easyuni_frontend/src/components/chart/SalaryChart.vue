<template>
    <div class="py-3">
        <h3 class="mb-0">GES Median Salary</h3>
        <small class="text-muted text-small">Data provided by <a href="https://data.gov.sg/" target="_blank">Data.gov.sg</a></small>
        <LineChart :chartData="chartData" :chartOptions="chartOptions" />
    </div>
</template>

<script>
import LineChart from './LineChart'

export default {
    name: 'SalaryChart',
    components: {
        LineChart,
    },
    props: {
        chartData: {
            type: Object,
            required: true,
        },
    },
    data() {
        return {
            chartOptions: {
                maintainAspectRatio: false,
                responsive: true,
                plugins: {
                    legend: {
                        display: false
                    },
                    tooltip: {
                        callbacks: {
                            label: function(value) {
                                console.log(value)
                                return ' $' + value.formattedValue + '/month';
                            }
                        }
                    }
                },
                fill: true,
                backgroundColor: 'rgba(255, 98, 88, 0.5)',
                tension: 0.4,
                pointBorderWidth: 2,
                borderColor: "rgba(255, 98, 88, 1)",
                pointBackgroundColor: "rgba(255, 255, 255, 1)",
                pointRadius: 5,
                pointHoverRadius: 10,
                pointHoverBackgroundColor: "rgba(255, 255, 255, 0)",
                scales: {
                    y: {
                        ticks: {
                            stepSize: 100,
                            callback: function(value, index, ticks) {
                                return '$' + value;
                            }
                        },
                        offset: true,
                        suggestedMin: 2500,
                        grid: {
                            display: true,
                        },
                    },
                    x: {
                        grid: {
                            display: false,
                        },
                    }
                }
            },
        }
    },
}
</script>