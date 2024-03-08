document.addEventListener('DOMContentLoaded', main);

function main(){
	if(!window.location.href.endsWith('/edit')){
        const add_btn = document.querySelector('.add_btn');
		add_btn.addEventListener('click', toggleCreateContactForm);

	}else{
		hideSaveButton();
	}
}

function hideSaveButton(){
	const saveBtn = document.querySelector('.save_task');
	saveBtn.classList.add('hidden');
}

function toggleCreateContactForm(e){
	e.target.classList.toggle('hidden');
	const addContactForm = document.querySelector('#new_contact_form_div');
	addContactForm.classList.toggle('hidden');
	const update_btn = document.querySelector('.update_task');
	update_btn.classList.toggle('hidden');
}