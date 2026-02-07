ALTER TABLE public.tb_pagamento
ADD COLUMN status varchar(25) DEFAULT 'PENDENTE_PAGAMENTO' NOT NULL;

ALTER TABLE public.tb_pagamento_aud
ADD COLUMN status varchar(25);

ALTER TABLE public.tb_pagamento
ADD CONSTRAINT chk_status_pagamento
CHECK (status IN ('PENDENTE_PAGAMENTO', 'APROVADO_PAGAMENTO', 'REPROVADO_PAGAMENTO'));