theme: /Tech

    state: AddressTest
        q!: test address

        state: Address
            q: * $Address *
            a: Address: {{ toPrettyString( $parseTree._Address ) }}

        state: IncompleteAddress
            q: * $IncompleteAddress *
            a: IncompleteAddress: {{ toPrettyString( $parseTree._IncompleteAddress ) }}

        state: WithoutStreet
            q: * $NonExplicitStreet *
            a: NonExplicitStreet: {{ toPrettyString( $parseTree._NonExplicitStreet ) }}