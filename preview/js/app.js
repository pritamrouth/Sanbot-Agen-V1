let isListening = false;

function showScreen(screenId) {
    document.querySelectorAll('.screen').forEach(screen => {
        screen.classList.remove('active');
    });
    document.getElementById(screenId).classList.add('active');
}

function showToast(message) {
    const toast = document.getElementById('toast');
    toast.textContent = message;
    toast.classList.add('show');
    setTimeout(() => {
        toast.classList.remove('show');
    }, 2000);
}

function toggleVoice() {
    const micButton = document.getElementById('micButton');
    const voiceStatus = document.getElementById('voiceStatus');
    const waveform = document.getElementById('waveform');
    const instructionText = document.getElementById('instructionText');
    
    isListening = !isListening;
    
    if (isListening) {
        micButton.classList.add('active');
        voiceStatus.textContent = 'Listening...';
        waveform.classList.add('active');
        instructionText.textContent = 'Tap to stop';
        
        setTimeout(() => {
            if (isListening) {
                stopListeningAndRespond();
            }
        }, 3000);
    } else {
        stopListening();
    }
}

function stopListening() {
    const micButton = document.getElementById('micButton');
    const voiceStatus = document.getElementById('voiceStatus');
    const waveform = document.getElementById('waveform');
    const instructionText = document.getElementById('instructionText');
    
    isListening = false;
    micButton.classList.remove('active');
    voiceStatus.textContent = 'Tap to speak';
    waveform.classList.remove('active');
    instructionText.textContent = 'Tap to speak';
}

function stopListeningAndRespond() {
    stopListening();
    
    const conversation = document.getElementById('conversation');
    const voiceStatus = document.getElementById('voiceStatus');
    
    voiceStatus.textContent = 'Processing...';
    
    const userMessage = document.createElement('div');
    userMessage.className = 'message user';
    userMessage.innerHTML = '<div class="message-role">You</div>Show me Dubai packages';
    conversation.appendChild(userMessage);
    
    setTimeout(() => {
        voiceStatus.textContent = 'Speaking...';
        
        const agentMessage = document.createElement('div');
        agentMessage.className = 'message agent';
        agentMessage.innerHTML = '<div class="message-role">Agent</div>Here are our top Dubai packages! We have the Dubai Desert Safari for AED 299, Burj Khalifa Tour for AED 199, and Dubai Marina Cruise for AED 149.';
        conversation.appendChild(agentMessage);
        conversation.scrollTop = conversation.scrollHeight;
        
        setTimeout(() => {
            voiceStatus.textContent = 'Tap to speak';
        }, 2000);
    }, 1500);
}

function submitForm() {
    const nameInput = document.getElementById('nameInput');
    const phoneInput = document.getElementById('phoneInput');
    
    if (!nameInput.value || !phoneInput.value) {
        showToast('Please fill in required fields');
        return;
    }
    
    showScreen('thankyouScreen');
}

document.getElementById('welcomeScreen').addEventListener('click', function(e) {
    if (!e.target.classList.contains('start-button')) {
        showScreen('menuScreen');
    }
});
