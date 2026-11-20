export const isEligible = (attend) => {
    if (attend < 0 || attend > 100) {
        throw new Error("Attendance must be between 0 and 100");
    }
    return attend >= 75;
}