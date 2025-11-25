document.addEventListener("DOMContentLoaded", function () {
    handleSeatSelectionSummary();
    setupPosterPreview();
});

/**
 * Convert internal "row-col" like "1-3" to "A3"
 */
function seatCodeFromValue(v) {
    if (typeof v !== "string") return v;
    if (!v.includes("-")) return v;

    const [rowStr, colStr] = v.split("-");
    const rowNum = parseInt(rowStr, 10);
    if (isNaN(rowNum)) return v;

    const letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    const idx = rowNum - 1;
    if (idx < 0 || idx >= letters.length) return v;

    return letters[idx] + colStr;
}

/**
 * Show count + tiered total price for selected seats on booking_seats.html
 * Expects checkboxes with:
 *  - name="selectedSeats"
 *  - data-price="<seatPrice>"
 */
function handleSeatSelectionSummary() {
    const seatCheckboxes = document.querySelectorAll('input[name="selectedSeats"]');
    if (!seatCheckboxes || seatCheckboxes.length === 0) return; // not on seat page

    const summaryEl = document.getElementById("seatSummary");
    if (!summaryEl) return;

    function updateSummary() {
        const selected = Array.from(seatCheckboxes).filter(cb => cb.checked);
        const count = selected.length;

        if (count === 0) {
            summaryEl.textContent = "No seats selected.";
            return;
        }

        const seatLabels = selected.map(cb => seatCodeFromValue(cb.value));

        // Sum using per-seat data-price from HTML
        let total = 0;
        selected.forEach(cb => {
            const raw = cb.dataset.price;
            const seatPrice = parseFloat(raw);
            if (!isNaN(seatPrice)) {
                total += seatPrice;
            }
        });

        let text = `Selected ${count} seat(s): ${seatLabels.join(", ")}`;
        if (total > 0) {
            text += ` | Estimated total: ${total.toFixed(2)}`;
        }

        summaryEl.textContent = text;
    }

    seatCheckboxes.forEach(cb => cb.addEventListener("change", updateSummary));
    updateSummary();
}

/**
 * Live movie poster preview from URL input
 * Expects:
 *  - input#posterUrlInput
 *  - img#posterPreview
 *  - optional div#posterPreviewNote
 */
function setupPosterPreview() {
    const input = document.getElementById("posterUrlInput");
    const img = document.getElementById("posterPreview");
    const note = document.getElementById("posterPreviewNote");

    if (!input || !img) return; // not on movie form

    function updatePreview() {
        const url = input.value.trim();

        if (!url) {
            img.style.display = "none";
            if (note) {
                note.textContent = "Paste an image URL to see the poster preview.";
            }
            return;
        }

        img.style.display = "block";
        img.src = url;
        if (note) {
            note.textContent = "";
        }
    }

    input.addEventListener("input", updatePreview);
    // run once on load (for edit mode when poster is already there)
    updatePreview();
}
