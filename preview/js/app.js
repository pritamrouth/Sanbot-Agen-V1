function navigateToMenu() {
    const welcomeScreen = document.getElementById('welcomeScreen');
    const menuScreen = document.getElementById('menuScreen');
    
    welcomeScreen.classList.remove('active');
    menuScreen.classList.add('active');
    
    animateMenuEntry();
}

function navigateToWelcome() {
    const welcomeScreen = document.getElementById('welcomeScreen');
    const menuScreen = document.getElementById('menuScreen');
    
    menuScreen.classList.remove('active');
    welcomeScreen.classList.add('active');
    
    resetWelcomeAnimations();
}

function animateMenuEntry() {
    const menuTitle = document.getElementById('menuTitle');
    const cards = document.querySelectorAll('.menu-card');
    
    menuTitle.style.opacity = '0';
    menuTitle.style.transform = 'translateY(20px)';
    
    cards.forEach((card, index) => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(50px)';
    });
    
    setTimeout(() => {
        menuTitle.style.transition = 'all 0.4s ease';
        menuTitle.style.opacity = '1';
        menuTitle.style.transform = 'translateY(0)';
    }, 100);
    
    cards.forEach((card, index) => {
        setTimeout(() => {
            card.style.transition = 'all 0.4s ease';
            card.style.opacity = '1';
            card.style.transform = 'translateY(0)';
        }, 200 + (index * 100));
    });
}

function resetWelcomeAnimations() {
    const logo = document.getElementById('logo');
    const title = document.getElementById('welcomeTitle');
    const subtitle = document.getElementById('welcomeSubtitle');
    const button = document.getElementById('startButton');
    
    logo.style.animation = 'none';
    title.style.animation = 'none';
    subtitle.style.animation = 'none';
    button.style.animation = 'none';
    
    void logo.offsetWidth;
    
    logo.style.animation = 'fadeIn 0.5s ease forwards';
    title.style.animation = 'slideUp 0.4s ease 0.3s forwards';
    subtitle.style.animation = 'slideUp 0.4s ease 0.5s forwards';
    button.style.animation = 'scaleIn 0.4s ease 0.7s forwards, pulse 2s ease-in-out 1.1s infinite';
}

function showToast(message) {
    const toast = document.getElementById('toast');
    toast.textContent = message + ' - Coming Soon';
    toast.classList.add('show');
    
    setTimeout(() => {
        toast.classList.remove('show');
    }, 2000);
}

document.getElementById('welcomeScreen').addEventListener('click', function(e) {
    if (e.target.id !== 'startButton') {
        navigateToMenu();
    }
});
