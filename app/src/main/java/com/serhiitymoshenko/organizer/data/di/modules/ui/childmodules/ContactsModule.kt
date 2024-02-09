package com.serhiitymoshenko.organizer.data.di.modules.ui.childmodules

import com.serhiitymoshenko.organizer.ui.home.contacts.repositories.ContactsRepository
import com.serhiitymoshenko.organizer.ui.home.contacts.addcontact.viewmodel.AddContactViewModel
import com.serhiitymoshenko.organizer.ui.home.contacts.viewmodel.ContactsHomeViewModel
import org.koin.dsl.module

val contactsModule = module {

    single<ContactsRepository> {
        ContactsRepository(get())
    }

    single<ContactsHomeViewModel> {
        ContactsHomeViewModel(get())
    }

    single<AddContactViewModel> {
        AddContactViewModel(get())
    }
}