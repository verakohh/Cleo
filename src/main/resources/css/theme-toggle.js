const toggleButton = document.getElementById('toggle-mode');
const themeLink = document.getElementById('theme-link');

// Set default mode to light mode
let isDarkMode = false;

toggleButton.addEventListener('click', function() {
    if (isDarkMode) {
        themeLink.setAttribute('href', 'light-mode.css');
        toggleButton.textContent = 'Switch to Dark Mode';
    } else {
        themeLink.setAttribute('href', 'dark-mode.css');
        toggleButton.textContent = 'Switch to Light Mode';
    }
    isDarkMode = !isDarkMode;
});
